package ru.rrozhkov.easykin.android.db.impl;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rrozhkov on 6/19/2017.
 */

public class DumpManager {
    public static String dump = "INSERT INTO category(id, name) VALUES(1, 'Дом');INSERT INTO category(id, name) VALUES(2, 'Дети');INSERT INTO category(id, name) VALUES(3, 'Семья');INSERT INTO category(id, name) VALUES(4, 'Машина');INSERT INTO category(id, name) VALUES(5, 'Финансы');INSERT INTO category(id, name) VALUES(6, 'Платежи');INSERT INTO category(id, name) VALUES(7, 'Документы');INSERT INTO category(id, name) VALUES(8, 'Работа');INSERT INTO category(id, name) VALUES(9, 'Задачи');INSERT INTO category(id, name) VALUES(10, 'Коммунальные услуги');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(3, 'Берсенева', 'Серафима', 'Андреевна', 'Ж', 'null', 'null');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(4, 'Рожков', 'Алексей', 'Романович', 'М', 'null', 'null');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(1, 'Рожков', 'Роман', 'Александрович', 'М', 'rrozhkov', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=');INSERT INTO person (id, surname, name, secondname, sex, username, password) VALUES(2, 'Берсенева', 'Татьяна', 'Михайловна', 'Ж', 'tberseneva', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=');INSERT INTO task(id, name, priority, category, status) VALUES(111, 'Заменить зимнюю резину $1000$ р', 1, 4, 1);INSERT INTO task(id, name, priority, category, status) VALUES(166, 'Прополоть картошку', 1, 3, 1);INSERT INTO task(id, name, priority, category, status) VALUES(179, 'TFS-46477', 1, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(178, 'Съездить в Леруа и на Южный', 1, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(177, 'Съездить на Черлак за фруктами и овощами', 1, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(172, 'Отремонтировать колесики у диванчика', 1, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(87, 'Сделать Лешке полку для игрушек', 1, 2, 1);INSERT INTO task(id, name, priority, category, status) VALUES(131, 'Записать Лешку на прием к стоматологу', 1, 2, 1);INSERT INTO task(id, name, priority, category, status) VALUES(22, 'Пройти диагностику двигателя', 1, 4, 1);INSERT INTO task(id, name, priority, category, status) VALUES(157, 'Купить Симке сапоги/ботинки на осень', 1, 2, 1);INSERT INTO task(id, name, priority, category, status) VALUES(12, 'Поменять прокладку ГБЦ и прокладку свечных колодцев', 2, 4, 1);INSERT INTO task(id, name, priority, category, status) VALUES(13, 'Отремонтировать подсветку приборной панели', 2, 4, 1);INSERT INTO task(id, name, priority, category, status) VALUES(30, 'Субсидии по оплате коммунальных услуг', 2, 3, 1);INSERT INTO task(id, name, priority, category, status) VALUES(86, 'Укрепить канализациооную трубу под раковиной в ванной', 2, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(48, 'Отремонтировать компьютер', 2, 2, 1);INSERT INTO task(id, name, priority, category, status) VALUES(6, 'Купить Тане лампу на струпцине', 2, 3, 1);INSERT INTO task(id, name, priority, category, status) VALUES(16, 'Подать заявку на утепление щелей', 2, 10, 1);INSERT INTO task(id, name, priority, category, status) VALUES(173, 'TFS-45797', 2, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(175, 'TFS-45512', 2, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(174, 'TFS-46492', 2, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(176, 'TFS-46527', 2, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(161, 'Выкачать сливную яму в частном доме $600$ р', 2, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(32, '5.0.1 abeaf16b6938db417f945b6742780052512b069c', 3, 8, 1);INSERT INTO task(id, name, priority, category, status) VALUES(76, 'И оставит человек отца и мать своих, и прилепится к жене своей, и будут двое едина плоть', 3, 3, 1);INSERT INTO task(id, name, priority, category, status) VALUES(79, 'Контакты', 3, 7, 1);INSERT INTO task(id, name, priority, category, status) VALUES(106, 'Мне в Германию нельзя, у меня дед воевал. Боюсь сорвусь.', 3, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(152, 'EasyKin Landing', 3, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(151, 'ОЭК', 3, 10, 1);INSERT INTO task(id, name, priority, category, status) VALUES(150, 'Росводоканал Омск', 3, 10, 1);INSERT INTO task(id, name, priority, category, status) VALUES(167, 'EasyKin UI Framework', 3, 1, 1);INSERT INTO task(id, name, priority, category, status) VALUES(28, 'Путь к dblink \\\\172.30.206.65\\fobo_distributor\\ ', 3, 8, 1)";

    public static void restoreDump(EasyKinDBHelper dbHelper){
//        String dump = readDump();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(!db.isOpen())
            return;
        String[] sqls = dump.split(";");
        try {
            for (String sql : sqls) {
                db.execSQL(sql);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
    }
}
