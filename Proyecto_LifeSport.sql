-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-04-2024 a las 09:24:37
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Proyecto LifeSport`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Carga_Usuario`
--

CREATE TABLE `Carga_Usuario` (
  `Id_Registro` int NOT NULL,
  `Fecha` datetime NOT NULL,
  `carga` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Empleados`
--

CREATE TABLE `Empleados` (
  `Id_Empleado` int NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellidos` varchar(30) NOT NULL,
  `Correo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Gimnasio`
--

CREATE TABLE `Gimnasio` (
  `Id_Gimnasio` int NOT NULL,
  `Fecha_Entrada` datetime NOT NULL,
  `Fecha_Salida` datetime NOT NULL,
  `Pago` int NOT NULL,
  `Compras` int NOT NULL,
  `Cantidad_Usuarios` int NOT NULL,
  `Empleados` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_usuarios`
--

CREATE TABLE `registro_usuarios` (
  `Id` int NOT NULL,
  `Nombre_Usuario` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `Contraseña` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Altura` int NOT NULL,
  `Peso` int NOT NULL,
  `Edad` int NOT NULL,
  `Correo` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='TABLA DE REGISTRO DE USUARIOS';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Rutina`
--

CREATE TABLE `Rutina` (
  `Id_Rutina` int NOT NULL,
  `Fecha_Inicio` date NOT NULL,
  `Fecha_Fin` date NOT NULL,
  `Tipo_Ejercicio` varchar(50) NOT NULL,
  `Carga` int NOT NULL,
  `Series` int NOT NULL,
  `Descanso` time NOT NULL,
  `Musculo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='TABLA DE RUTINAS';


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sportlife`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_usuarios`
--

CREATE TABLE `registro_usuarios` (
  `Id` int(11) NOT NULL,
  `Nombre_Usuario` varchar(30) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `Altura` int(250) NOT NULL,
  `Peso` int(160) NOT NULL,
  `Edad` int(130) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='TABLA DE REGISTRO DE USUARIOS';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `registro_usuarios`
--
ALTER TABLE `registro_usuarios`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registro_usuarios`
--
ALTER TABLE `registro_usuarios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Carga_Usuario`
--
ALTER TABLE `Carga_Usuario`
  ADD PRIMARY KEY (`Id_Registro`);

--
-- Indices de la tabla `Empleados`
--
ALTER TABLE `Empleados`
  ADD PRIMARY KEY (`Id_Empleado`);

--
-- Indices de la tabla `Gimnasio`
--
ALTER TABLE `Gimnasio`
  ADD PRIMARY KEY (`Id_Gimnasio`);

--
-- Indices de la tabla `registro_usuarios`
--
ALTER TABLE `registro_usuarios`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `Rutina`
--
ALTER TABLE `Rutina`
  ADD PRIMARY KEY (`Id_Rutina`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Carga_Usuario`
--
ALTER TABLE `Carga_Usuario`
  MODIFY `Id_Registro` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Empleados`
--
ALTER TABLE `Empleados`
  MODIFY `Id_Empleado` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Gimnasio`
--
ALTER TABLE `Gimnasio`
  MODIFY `Id_Gimnasio` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `registro_usuarios`
--
ALTER TABLE `registro_usuarios`
  MODIFY `Id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Rutina`
--
ALTER TABLE `Rutina`
  MODIFY `Id_Rutina` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
