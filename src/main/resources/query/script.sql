-- Insert Authors
INSERT INTO authors (name, birth, created_at, updated_at) VALUES
('J.K. Rowling', '1965-07-31', NOW(), NOW()),
('George R.R. Martin', '1948-09-20', NOW(), NOW()),
('J.R.R. Tolkien', '1892-01-03', NOW(), NOW()),
('Stephen King', '1947-09-21', NOW(), NOW()),
('Agatha Christie', '1890-09-15', NOW(), NOW()),
('Mark Twain', '1835-11-30', NOW(), NOW()),
('Isaac Asimov', '1920-01-02', NOW(), NOW()),
('Arthur Conan Doyle', '1859-05-22', NOW(), NOW()),
('Ernest Hemingway', '1899-07-21', NOW(), NOW()),
('F. Scott Fitzgerald', '1896-09-24', NOW(), NOW());
--

select * from authors;

-- Insert Books
INSERT INTO books (name, publish_date, created_at, updated_at, author_id) VALUES
('Harry Potter and the Philosopher\'s Stone', '1997-06-26', NOW(), NOW(), 1),
('Harry Potter and the Chamber of Secrets', '1998-07-02', NOW(), NOW(), 1),
('A Game of Thrones', '1996-08-06', NOW(), NOW(), 2),
('A Clash of Kings', '1998-11-16', NOW(), NOW(), 2),
('The Hobbit', '1937-09-21', NOW(), NOW(), 3),
('The Lord of the Rings', '1954-07-29', NOW(), NOW(), 3),
('The Shining', '1977-01-28', NOW(), NOW(), 4),
('It', '1986-09-15', NOW(), NOW(), 4),
('Murder on the Orient Express', '1934-01-01', NOW(), NOW(), 5),
('And Then There Were None', '1939-11-06', NOW(), NOW(), 5),
('Adventures of Huckleberry Finn', '1885-12-10', NOW(), NOW(), 6),
('The Adventures of Tom Sawyer', '1876-06-01', NOW(), NOW(), 6),
('Foundation', '1951-06-01', NOW(), NOW(), 7),
('I, Robot', '1950-12-02', NOW(), NOW(), 7),
('A Study in Scarlet', '1887-11-01', NOW(), NOW(), 8),
('The Hound of the Baskervilles', '1902-04-01', NOW(), NOW(), 8),
('The Old Man and the Sea', '1952-09-01', NOW(), NOW(), 9),
('A Farewell to Arms', '1929-09-27', NOW(), NOW(), 9),
('The Great Gatsby', '1925-04-10', NOW(), NOW(), 10),
('This Side of Paradise', '1920-03-26', NOW(), NOW(), 10);
--