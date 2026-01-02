String sql =
    "SELECT b.Title, " +
    "       CONCAT(a.Name, ' ', a.Surname) AS AuthorName, " +
    "       b.ISBN, " +
    "       b.YearPublished, " +
    "       c.Name AS CategoryName, " +
    "       b.Copies " +
    "FROM Books b " +
    "LEFT JOIN Authors a ON b.AuthorID = a.AuthorID " +
    "LEFT JOIN Categories c ON b.CategoryID = c.CategoryID " +
    "ORDER BY b.Title";

String sql =
        "SELECT b.Title, " +
        "       CONCAT(a.Name, ' ', a.Surname) AS AuthorName, " +
        "       CASE WHEN b.Copies > 0 THEN 'Available' ELSE 'Unavailable' END AS Availability " +
        "FROM Books b " +
        "JOIN Authors a ON b.AuthorID = a.AuthorID " +
        "WHERE a.Name LIKE ? OR a.Surname LIKE ? " +
        "   OR CONCAT(a.Name, ' ', a.Surname) LIKE ?";
    
String sql =
        "SELECT b.Title, " +
        "       CONCAT(a.Name, ' ', a.Surname) AS AuthorName, " +
        "       CASE WHEN b.Copies > 0 THEN 'Available' ELSE 'Unavailable' END AS Availability " +
        "FROM Books b " +
        "LEFT JOIN Authors a ON b.AuthorID = a.AuthorID " +
        "WHERE b.Title LIKE ? OR b.ISBN LIKE ?";