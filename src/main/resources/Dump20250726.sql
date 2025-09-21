-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: elektronska_prodavnica
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kategorija_proizvoda`
--

DROP TABLE IF EXISTS `kategorija_proizvoda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategorija_proizvoda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorija_proizvoda`
--

LOCK TABLES `kategorija_proizvoda` WRITE;
/*!40000 ALTER TABLE `kategorija_proizvoda` DISABLE KEYS */;
INSERT INTO `kategorija_proizvoda` VALUES (1,'Televizori i audio oprema'),(2,'Računari i laptopovi'),(3,'Mobilni telefoni i dodaci'),(4,'Kućni aparati');
/*!40000 ALTER TABLE `kategorija_proizvoda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `datum_rodjenja` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tip_korisnika_id` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `korisnicko_ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `sifra` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq32tdodxamogrno1442s49svo` (`tip_korisnika_id`),
  CONSTRAINT `FKq32tdodxamogrno1442s49svo` FOREIGN KEY (`tip_korisnika_id`) REFERENCES `tip_korisnika` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('2001-04-27',1,1,'vidojesrdanovic123@gmail.com','Vidoje','admin','Srdanovic','$2a$10$Tgs1RO/8LkpGnVC4Edl7nOZnKXXM3b2PA2CYaLq8HlhPjKPvNZ/MG'),('1993-07-21',2,2,'majahaha@yahoo.com','Maja','maja','Majic','$2a$10$Tgs1RO/8LkpGnVC4Edl7nOZnKXXM3b2PA2CYaLq8HlhPjKPvNZ/MG'),('1987-01-01',3,2,'perohehe@yaho.com','Petar','pero','Zmaj','$2a$10$Tgs1RO/8LkpGnVC4Edl7nOZnKXXM3b2PA2CYaLq8HlhPjKPvNZ/MG');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korpa`
--

DROP TABLE IF EXISTS `korpa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korpa` (
  `kolicina` int NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `korisnik_id` bigint NOT NULL,
  `proizvod_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhyp20wugm4ondn07n5vbadptx` (`korisnik_id`),
  KEY `FKrxpfnfmihy2wy0hl6rw41pjeb` (`proizvod_id`),
  CONSTRAINT `FKhyp20wugm4ondn07n5vbadptx` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `FKrxpfnfmihy2wy0hl6rw41pjeb` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korpa`
--

LOCK TABLES `korpa` WRITE;
/*!40000 ALTER TABLE `korpa` DISABLE KEYS */;
INSERT INTO `korpa` VALUES (5,2,1,8),(2,3,1,9);
/*!40000 ALTER TABLE `korpa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porudzbina`
--

DROP TABLE IF EXISTS `porudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porudzbina` (
  `datum` date NOT NULL,
  `ukupna_cena` double NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `korisnik_id` bigint NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlfa237dpcw2o7mbrtlvlof71c` (`korisnik_id`),
  CONSTRAINT `FKlfa237dpcw2o7mbrtlvlof71c` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porudzbina`
--

LOCK TABLES `porudzbina` WRITE;
/*!40000 ALTER TABLE `porudzbina` DISABLE KEYS */;
INSERT INTO `porudzbina` VALUES ('2025-07-26',49999.99,1,1,'isporučeno'),('2025-07-26',375996,2,3,'poslato'),('2025-07-26',69999,3,3,'u obradi'),('2025-07-26',79999.99,4,2,'otkazano'),('2025-07-26',149999.99,5,3,'u obradi');
/*!40000 ALTER TABLE `porudzbina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvod`
--

DROP TABLE IF EXISTS `proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proizvod` (
  `cena` double DEFAULT NULL,
  `kolicina` int DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `kategorija_id` bigint DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `proizvodjac` varchar(255) DEFAULT NULL,
  `slika` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm8klfatdwm7nyfbaa4907f0rt` (`kategorija_id`),
  CONSTRAINT `FKm8klfatdwm7nyfbaa4907f0rt` FOREIGN KEY (`kategorija_id`) REFERENCES `kategorija_proizvoda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvod`
--

LOCK TABLES `proizvod` WRITE;
/*!40000 ALTER TABLE `proizvod` DISABLE KEYS */;
INSERT INTO `proizvod` VALUES (49999.99,9,1,1,'Samsung Smart TV 50\"','50-inčni 4K Smart TV sa HDR podrškom','Samsung','https://images.ctshop.rs/product/img/led-televizori/samsung-ue50nu7402uxxh-smart-tv-504k-ultra-hd-dvb-t2/500x500/71180275_2197942999.webp'),(69999,6,2,1,'LG OLED55C11LB','55-inčni OLED televizor sa AI procesorom','LG','https://www.lg.com/rs/images/televizori/md07523692/gallery/D01.jpg'),(15999,19,3,1,'Sony Bluetooth zvučnik','Bežični zvučnik sa dugim trajanjem baterije','Sony','https://sony.scene7.com/is/image/sonyglobalsolutions/SRS-ULT1000-Primary-image?$mediaCarouselSmall$&fmt=png-alpha'),(149999.99,4,4,2,'Lenovo ThinkPad X1 Carbon','Poslovni laptop sa Intel i7 procesorom','Lenovo','https://p3-ofp.static.pub//fes/cms/2024/07/05/05dhzg0lrtq4i0d3wxqyjjakwmbmzr331426.png'),(119999,0,5,2,'Dell XPS 13','Ultrabook sa visokim performansama i malom težinom','Dell','https://m.media-amazon.com/images/I/710EGJBdIML.jpg'),(79999.99,7,6,2,'HP Pavilion Gaming','Gaming laptop sa NVIDIA grafikom i Ryzen procesorom','HP','https://images.ctshop.rs/img/products/2022-08-05/hp-pavilion-gaming-15-ec2072nm-633v9ea-gejmerski-laptop-15-6-quot-fhd-amd-ryzen-5-5600h-8gb-512gb-ssd-geforce-gtx1650-crni_llpht_3.webp'),(89999.99,15,7,3,'Apple iPhone 13','128 GB memorije, A15 čip, odlična kamera','Apple','https://m.media-amazon.com/images/I/61CpZkUmKQL._UF894,1000_QL80_.jpg'),(4999,25,8,3,'Xiaomi Mi Band 7','Pametna narukvica za praćenje aktivnosti','Xiaomi','https://img.ep-cdn.com/i/500/500/fb/fblhmyzaxvijdpckqsto/xiaomi-fitnes-narukvica-mi-smart-band-7-gl-cene.jpg'),(19999.99,18,9,4,'Bosch Usisivač BGLS4','Tih i snažan usisivač sa HEPA filterom','Bosch','https://media3.bsh-group.com/Product_Shots/5120x/MCSA03218623_J5215_2576747_BGLS4X201_CMYK_def.webp'),(34999,10,10,4,'Gorenje Ugradna rerna','Multifunkcionalna rerna sa EcoClean funkcijom','Gorenje','https://gor.hgecdn.net/medias/MB-PIMG-A4F2418169FC63072E183629BB7BB038-MABAGOR-515Wx515H?context=bWFzdGVyfG1hYmFnb3Jwcm9kdWN0aW1hZ2VzfDE5OTY0MnxpbWFnZS9wbmd8YURObEwyaGtNUzg1TWpnMk56YzNNamMwTXprNEwwMUNYMUJKVFVkZlFUUkdNalF4T0RFMk9VWkROak13TnpKRk1UZ3pOakk1UWtJM1FrSXdNemhmVFVGQ1FVZFBVaTAxTVRWWGVEVXhOVWd8NmM2MTQ5YjAxOWM3Y2UyZDg3NzI3MGJhMmZlMzlmMzM4MzA2NTNlYjRlZTcxMzFjZTZiNmRkNTFlNTc4NGEwYQ');
/*!40000 ALTER TABLE `proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_korisnika`
--

DROP TABLE IF EXISTS `tip_korisnika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tip_korisnika` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_korisnika`
--

LOCK TABLES `tip_korisnika` WRITE;
/*!40000 ALTER TABLE `tip_korisnika` DISABLE KEYS */;
INSERT INTO `tip_korisnika` VALUES (1,'ROLE_PRODAVAC'),(2,'ROLE_KUPAC');
/*!40000 ALTER TABLE `tip_korisnika` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-26  2:12:24
