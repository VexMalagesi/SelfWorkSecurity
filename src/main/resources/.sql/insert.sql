
-- INSERT INTO authors (firstname, lastname, email)
-- VALUES ('Vincenzo', 'Bianchi', 'vincblanchet@tin.it');

-- INSERT INTO authors (firstname, lastname, email)
-- VALUES ('Antonio', 'Rossi', 'antored@mail.it');

INSERT INTO authors (firstname, lastname, email)
VALUES ('Vincenzo', 'Bianchi', 'vincblanchet@tin.it');

INSERT INTO authors (firstname, lastname, email)
VALUES ('Antonio', 'Rossi', 'antored@mail.it');

INSERT INTO posts(title, body, publish_date, author_id)
SELECT 'Lorem ipsum.....', 'Ciao', '20260101', id
FROM authors
where firstname = 'Vincenzo'
and lastname = 'Bianchi';

INSERT INTO posts (title, body, publish_date, author_id)
SELECT 'Lorem ipsum.....', 'Non sono Ciao', '20260101', id
FROM authors
where firstname = 'Vincenzo'
and lastname = 'Bianchi';
