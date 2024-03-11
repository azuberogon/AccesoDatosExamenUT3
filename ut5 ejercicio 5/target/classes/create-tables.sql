CREATE TABLE `compras` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `id_objeto` int NOT NULL,
                           `id_comprador` int NOT NULL,
                           `id_vendedor` int NOT NULL,
                           `fecha_compra` date NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `articulos` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `nombre` varchar(45) DEFAULT NULL,
                             `valor` int NOT NULL,
                             `id_propietario` int NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `usuarios` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `full_name` varchar(45) DEFAULT NULL,
                            `user` varchar(45) NOT NULL,
                            `email` varchar(45) DEFAULT NULL,
                            `password` varchar(45) DEFAULT NULL,
                            `creation_date` date DEFAULT NULL,
                            `modification_date` date DEFAULT NULL,
                            `saldo` int NOT NULL DEFAULT '0',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `user_UNIQUE` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;
