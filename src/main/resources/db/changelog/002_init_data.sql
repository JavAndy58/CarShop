INSERT INTO details VALUES
(1, 'Реле', 100 , 150, DEFAULT ),
(2, 'Шрус', 1750 , 2100, DEFAULT ),
(3, 'Магнитола', 2500 , 2980, DEFAULT ),
(4, 'Клипса', 25 , 38, DEFAULT );

INSERT INTO customers VALUES
(1, 'Иванов Пётр', '+79273893138'),
(2, 'Ершое Евгений', '+73255693898'),
(3,'Макаров Владимир', '+79995256535');

INSERT INTO cars VALUES
(1, 'Focus 2', 'XXEERTY525SA626', 2),
(2, 'Logan 1', 'TTRERTY525SA222', 2),
(3, 'Lada Granta', 'WWRTYY525SA655', 2),
(4, 'Audi 100', 'WAGRTTY525SA525', 1);

INSERT INTO orders VALUES
(1, '2022-11-10 10:11:00', 1000.0, DEFAULT, DEFAULT, null, 1, 4),
(2, '2022-11-8 9:00:00', 5000, DEFAULT, DEFAULT, null, 2, 2),
(3, '2022-11-20 10:00:00', 2500, DEFAULT, DEFAULT, 'Клиент заберет в 24 ноября', 3, null);

INSERT INTO sets VALUES
(1, 2, 1, 1),
(2, 1, 2, 3),
(3, 10, 3, 4);


