CREATE DATABASE sportLife;

CREATE TABLE `Usuario` (
  `id` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `correoElectronico` varchar(255) NOT NULL,
  `fechaDeNacimiento` varchar(45) DEFAULT NULL,
  `peso` varchar(45) DEFAULT NULL,
  `altura` varchar(45) DEFAULT NULL,
  `frecuenciaDeEjercicio` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



INSERT INTO `Usuario` (`id`, `nombre`, `contraseña`, `correoElectronico`, `fechaDeNacimiento`, `peso`, `altura`, `frecuenciaDeEjercicio`) VALUES
(1, 'amf', '123', 'amfspain', '08/04/2002', '60', '180', 'Diariamente');


ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `Usuario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;


