INSERT INTO PROFESSION (title,notes)  VALUES ( 'Менеджер по работе с клиентами', 'Примечания');
INSERT INTO PROFESSION (title,notes)  VALUES ( 'Менеджер по продажам', 'Очень важные примечания');
INSERT INTO PROFESSION (title,notes)  VALUES ( 'Исполнительный директор', 'Примечания');
INSERT INTO PROFESSION (title,notes)  VALUES ( 'Генеральный директор', 'Очень важные примечания');
INSERT INTO PROFESSION (title,notes)  VALUES ( 'Технический директор', 'Очень важные примечания');
INSERT INTO PROFESSION (title,notes)  VALUES ( 'Директор по развитию', 'Примечания');

INSERT INTO DEPARTMENT (title,parent_id,notes)  VALUES ( 'Главный отдел',NULL , 'Очень важные примечания');
INSERT INTO DEPARTMENT (title,parent_id,notes)  VALUES ( 'Отдел продаж',1, 'Примечания');
INSERT INTO DEPARTMENT (title,parent_id,notes) VALUES ( 'Рекламный отдел ',1, 'Очень важные примечания ');
INSERT INTO DEPARTMENT (title,parent_id,notes) VALUES ( 'Аналитический отдел',1, 'Примечания');
INSERT INTO DEPARTMENT (title,parent_id,notes) VALUES ( 'Отдел резработки ',1, 'Очень важные примечания');
INSERT INTO DEPARTMENT (title,parent_id,notes) VALUES ( 'Отдел по работе с клиентами',2, 'Очень важные примечания ');

INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Иванов И. И. ',1, 6);
INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Петров П. П. ', 2,2);
INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Сидоров С. С. ', 3,1);
INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Лебедев А. А. ', 3,3);
INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Матвеев М. М.', 5,1);
INSERT INTO EMPLOYEE (full_name,profession_id,department_id)  VALUES ( 'Летов И. Ф. ', 6,5);