package com.example.demo.service;

import com.example.demo.dto.KategorijaDTO;
import com.example.demo.dto.PorudzbinaDTO;
import com.example.demo.dto.ProizvodDTO;
import com.example.demo.model.KategorijaProizvoda;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Porudzbina;
import com.example.demo.repository.KategorijaProizvodaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.PorudzbinaRepository;
import com.example.demo.repository.ProizvodRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class ProdavnicaRestService {

    @Autowired
    ProizvodRepository proizvodRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KategorijaProizvodaRepository kategorijaProizvodaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public ProizvodDTO findById(Long id){
        if (proizvodRepository.findById(id).isEmpty()){
            return null;
        }
        ProizvodDTO proizvodDTO = new ProizvodDTO();
        BeanUtils.copyProperties(proizvodRepository.findById(id).get(), proizvodDTO);
        return proizvodDTO;
    }

    public PorudzbinaDTO updateStatus(Long id, String noviStatus) {
        return porudzbinaRepository.findById(id)
                .map(porudzbina -> {
                    porudzbina.setStatus(noviStatus);
                    porudzbinaRepository.save(porudzbina);

                    PorudzbinaDTO dto = new PorudzbinaDTO();
                    dto.setId(porudzbina.getId());
                    dto.setDatum(porudzbina.getDatum());
                    dto.setStatus(porudzbina.getStatus());
                    dto.setUkupnaCena(porudzbina.getUkupnaCena());
                    dto.setKorisnikId(porudzbina.getKorisnik().getId());
                    dto.setKorisnickoIme(porudzbina.getKorisnik().getKorisnickoIme());

                    return dto;
                }).orElse(null);
    }

    public KategorijaDTO dodajKategoriju(KategorijaDTO dto) {
        KategorijaProizvoda kategorija = new KategorijaProizvoda();
        kategorija.setNaziv(dto.getNaziv());

        KategorijaProizvoda sacuvana = kategorijaProizvodaRepository.save(kategorija);

        KategorijaDTO result = new KategorijaDTO();
        result.setId(sacuvana.getId());
        result.setNaziv(sacuvana.getNaziv());

        return result;
    }

    public List<Porudzbina> findByKorisnikId(Long korisnikId) {
        return porudzbinaRepository.findByKorisnikId(korisnikId);
    }





    public List<Porudzbina> findNeisporucene() {
        return porudzbinaRepository.findByStatusNot("isporuceno");
    }

    public void generisiIzvestajNeisporucene(List<Porudzbina> porudzbine) {
        try {
            InputStream izvestajStream = getClass().getResourceAsStream("/izvestaji/neisporucene.jrxml");
            JasperReport report = JasperCompileManager.compileReport(izvestajStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(porudzbine);
            System.out.println("Putanja do JRXML fajla: " + getClass().getResource("/izvestaji/neisporucene.jrxml"));
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

            String folderPath = "src/main/resources/generated-reports/";
            Files.createDirectories(Path.of(folderPath));

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = folderPath + "neisporucene_" + timestamp + ".pdf";

            JasperExportManager.exportReportToPdfFile(print, filename);
            System.out.println("Izvestaj sacuvan: " + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Korisnik findKorisnikById(Long id){
        return korisnikRepository.findById(id).orElse(null);
    }

    public void generisiIzvestajZaKorisnika(List<Porudzbina> porudzbine, Long korisnikId) {
        try {
            InputStream izvestajStream = getClass().getResourceAsStream("/izvestaji/porudzbine_po_korisniku.jrxml");
            JasperReport report = JasperCompileManager.compileReport(izvestajStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(porudzbine);
            System.out.println("Putanja do JRXML fajla: " + getClass().getResource("/izvestaji/porudzbine_po_korisniku.jrxml"));

            Korisnik k = findKorisnikById(korisnikId);

            HashMap<String, Object> params = new HashMap<>();
            params.put("korisnicko_ime", k.getKorisnickoIme()); // ako šablon koristi parametre

            JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);

            String folderPath = "src/main/resources/generated-reports/";
            Files.createDirectories(Path.of(folderPath));

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = folderPath + "porudzbine_korisnik_" + korisnikId + "_" + timestamp + ".pdf";

            JasperExportManager.exportReportToPdfFile(print, filename);
            System.out.println("Izvestaj sacuvan: " + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}