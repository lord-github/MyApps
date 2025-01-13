-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Янв 13 2025 г., 11:22
-- Версия сервера: 10.4.24-MariaDB
-- Версия PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `etxt`
--

-- --------------------------------------------------------

--
-- Структура таблицы `finsh`
--

CREATE TABLE `finsh` (
  `tb` int(11) NOT NULL,
  `zaid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `otmena` int(11) NOT NULL,
  `tassyklanan` int(11) NOT NULL,
  `g` int(11) NOT NULL,
  `a` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `toleg_stat` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `ipaddress`
--

CREATE TABLE `ipaddress` (
  `tb` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `ip` int(11) NOT NULL,
  `tm` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `logggs`
--

CREATE TABLE `logggs` (
  `tb` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `ip` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `number`
--

CREATE TABLE `number` (
  `tb` int(11) NOT NULL,
  `num` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `stepbystep`
--

CREATE TABLE `stepbystep` (
  `stb` int(11) NOT NULL,
  `step` int(11) NOT NULL,
  `zaid` int(11) NOT NULL,
  `useriD` int(11) NOT NULL,
  `files` varchar(250) CHARACTER SET utf32 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `tempreq`
--

CREATE TABLE `tempreq` (
  `tb` int(11) NOT NULL,
  `md` varchar(60) NOT NULL,
  `uid` decimal(11,2) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `tipzadac`
--

CREATE TABLE `tipzadac` (
  `tb` int(11) NOT NULL,
  `name_zadaca` varchar(190) CHARACTER SET utf32 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `ustb` int(11) NOT NULL,
  `login` varchar(150) CHARACTER SET utf32 NOT NULL,
  `pass` varchar(30) CHARACTER SET utf8 NOT NULL,
  `fio` varchar(150) CHARACTER SET utf8 NOT NULL,
  `tip` int(11) NOT NULL,
  `jns` int(11) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `number` int(11) NOT NULL,
  `apikey` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `wywodrequest`
--

CREATE TABLE `wywodrequest` (
  `tb` int(11) NOT NULL,
  `num` int(8) NOT NULL,
  `ser` decimal(12,2) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `yumuslist`
--

CREATE TABLE `yumuslist` (
  `tb` int(11) NOT NULL,
  `zakazid` int(11) DEFAULT NULL,
  `tipID` int(11) DEFAULT NULL,
  `nameof` text CHARACTER SET utf8 DEFAULT NULL,
  `jemi` int(11) DEFAULT NULL,
  `ostalos` int(11) DEFAULT NULL,
  `bir_baha` decimal(10,2) DEFAULT NULL,
  `jyns` int(11) DEFAULT NULL,
  `dusun` text CHARACTER SET utf32 DEFAULT NULL,
  `url` text DEFAULT NULL,
  `tesgor` varchar(30) DEFAULT NULL,
  `simsan` int(11) DEFAULT NULL,
  `dili` varchar(19) CHARACTER SET utf32 DEFAULT NULL,
  `timestp` varchar(25) DEFAULT NULL,
  `act` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `finsh`
--
ALTER TABLE `finsh`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `ipaddress`
--
ALTER TABLE `ipaddress`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `logggs`
--
ALTER TABLE `logggs`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `number`
--
ALTER TABLE `number`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `stepbystep`
--
ALTER TABLE `stepbystep`
  ADD PRIMARY KEY (`stb`);

--
-- Индексы таблицы `tempreq`
--
ALTER TABLE `tempreq`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `tipzadac`
--
ALTER TABLE `tipzadac`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ustb`);

--
-- Индексы таблицы `wywodrequest`
--
ALTER TABLE `wywodrequest`
  ADD PRIMARY KEY (`tb`);

--
-- Индексы таблицы `yumuslist`
--
ALTER TABLE `yumuslist`
  ADD PRIMARY KEY (`tb`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `finsh`
--
ALTER TABLE `finsh`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `ipaddress`
--
ALTER TABLE `ipaddress`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `logggs`
--
ALTER TABLE `logggs`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `number`
--
ALTER TABLE `number`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `stepbystep`
--
ALTER TABLE `stepbystep`
  MODIFY `stb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `tempreq`
--
ALTER TABLE `tempreq`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `tipzadac`
--
ALTER TABLE `tipzadac`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `ustb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `wywodrequest`
--
ALTER TABLE `wywodrequest`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `yumuslist`
--
ALTER TABLE `yumuslist`
  MODIFY `tb` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
