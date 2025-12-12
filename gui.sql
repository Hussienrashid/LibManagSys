CREATE DATABASE LibraryDB2;
GO

USE LibraryDB2;
GO

-- ===========================
-- 1) USERS TABLE (for Add User)
-- ===========================
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Surname NVARCHAR(100) NOT NULL,
    Pin NVARCHAR(50) NOT NULL
);

-- ===========================
-- 2) CUSTOMERS (Members)
-- ===========================
CREATE TABLE Customers (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Surname NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(50),
    Age INT
);

-- ===========================
-- 3) AUTHORS
-- ===========================
CREATE TABLE Authors (
    AuthorID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Surname NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(50),
    Age INT
);

-- ===========================
-- 4) PUBLISHERS
-- ===========================
CREATE TABLE Publishers (
    PublisherID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Location NVARCHAR(200),
    Phone NVARCHAR(50)
);
-- ===========================
-- 5) CATEGORIES
-- ===========================
CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL UNIQUE
);
-- ===========================
-- 6) BOOKS
-- ===========================
CREATE TABLE Books (
    BookID INT IDENTITY(1,1) PRIMARY KEY,
    Title NVARCHAR(200) NOT NULL,
    AuthorID INT,
    PublisherID INT,
    ISBN NVARCHAR(50),
    YearPublished INT,
    CategoryID INT,
    Copies INT DEFAULT 1,
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
    FOREIGN KEY (PublisherID) REFERENCES Publishers(PublisherID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
-- ===========================
-- 7) LOANS (checkout + return)
-- ===========================
CREATE TABLE Loans (
    LoanID INT IDENTITY(1,1) PRIMARY KEY,
    CustomerID INT NOT NULL,
    BookID INT NOT NULL,
    CheckoutDate DATE NOT NULL,
    ReturnDate DATE,
    Returned BIT DEFAULT 0,

    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

/*insert*/ /*in addbook*/
INSERT INTO Authors (Name, Surname) VALUES
('J.K. Rowling', 'Rowling'),
('George Orwell', 'Orwell'),
('Stephen King', 'King'),
('Mark Twain', 'Twain'),
('Agatha Christie', 'Christie');
/*insert*/
INSERT INTO Publishers (Name) VALUES
('Penguin Books'),
('HarperCollins'),
('O’Reilly'),
('Oxford Press'),
('Random House');
/*insert*/
INSERT INTO Categories (Name) VALUES
('Fiction'),
('Science'),
('History'),
('Technology'),
('Kids');
/*test*/
SELECT * FROM Authors;
SELECT * FROM Publishers;
SELECT * FROM Categories;
/*check*/SELECT * FROM Books;
