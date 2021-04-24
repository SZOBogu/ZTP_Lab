package helper;

import entities.BookEntity;

public class BookPageInfoGenerator {
    public static String getHtmlPage(BookEntity book){
//       String html = "<!DOCTYPE html>\n" +
//               "<html xmlns:th=\"https://www.thymeleaf.org\">\n>" +
//               "<html lang=\"en\">\n" +
//               "<head>\n" +
//               "    <meta charset=\"UTF-8\">\n" +
//               "    <title>Book info</title>\n" +
//               "</head>\n" +
//               "<body>\n" +
//               book.getTitle() + " is the title of the book<br>\n" +
//               "Its authors name is: " + book.getAuthor() + "<br>\n" +
//               "It was released in " + book.getYear() + "<br>\n" +
//               "In database its id should be: " + book.getId() + "<br>\n" +
//               "\n" +
//               "<a href=\"/\"> Go back to home page</a>\n" +
//               "<a href=\"/dashboard\"> Go back to dashboard</a>\n" +
//               "</body>\n" +
//               "</html>";

        String html = "\n" +
                "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"https://www.thymeleaf.org\">\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Person shown</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<b th:text=\"${book.title}\"/> is the title of the book<br>\n" +
                "Its authors name is: <b th:text=\"${book.author}\"/><br>\n" +
                "It was released in <b th:text=\"${book.year}\"/><br>\n" +
                "In database its id should be: <b th:text=\"${book.id}\"/><br>\n" +
                "\n" +
                "<a href=\"/\"> Go back to home page</a>\n" +
                "<a href=\"/dashboard\"> Go back to dashboard</a>\n" +
                "</body>\n" +
                "</html>";
       return html;
    }
}
