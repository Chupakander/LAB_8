package servlets;

import bsu.rfe.java.group6.lab8.ChatMessage;
import bsu.rfe.java.group6.lab8.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.Calendar;


public class NewMessageServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;

    private String[] smiles = {":)", ";)", ":(", ":D"};

    public ImageIcon imageIcon = new ImageIcon();

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
// По умолчанию используется кодировка ISO-8859. Так как мы
// передаѐм данные в кодировке UTF-8
// то необходимо установить соответствующую кодировку HTTP-запроса
        request.setCharacterEncoding("UTF-8");
// Извлечь из HTTP-запроса параметр 'message'
        String message = (String) request.getParameter("message");
// Если сообщение не пустое, то
        if (message != null && !"".equals(message)) {
// По имени из сессии получить ссылку на объект ChatUser
            ChatUser author = activeUsers.get((String)
                    request.getSession().getAttribute("name"));
            if (message.contains(smiles[0])){
                imageIcon =  new ImageIcon("data:image/jpeg;base64,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");
            }
            else if (message.contains(smiles[1])){
                imageIcon =  new ImageIcon("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBUSFRgSFRUZGBgaGBoSHBkYGhgYGhISGhgZGhkZGBocIS4lHB4rIRgYJjgnLC8xNTU1GiQ7QDszPy40NTEBDAwMEA8QHhISHzQlJSw0NDQxNDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0MTQ0NDE0NDQ0NDQ0NP/AABEIAOEA4AMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABAUBAgMGB//EAEAQAAIBAgIHAwoDBwMFAAAAAAECAAMRBCEFEjFBUWFxBiKBEzJCUmKRobHB0XKC8BQzU5KisuEVQ9IWIyRE8f/EABoBAQACAwEAAAAAAAAAAAAAAAABBAIDBQb/xAAqEQADAAIBAwMDBQADAAAAAAAAAQIDEQQSITETQVEFMnEiQmGBoRQjUv/aAAwDAQACEQMRAD8A+zREQBERAEREAREQBE41KwXfOeszbBYcT9oBILCc2rqN85jDj0mJ+Ah3p08yUXqQD7zIbS8jQOKG7OY/aPZb3GRqum8Ov+4p/D3vlOLdpKA9InorfaYPNC90ZrFb8Jk/9o9lvcYGLG8EdQZBXtHQO8/ymdaem6DemB+K4+YkevH/AKRLxWvKZMTEqd86q4O+RkqUqmYKN0KmDhV9ElT1v85sVJ+DBpryS5mQSKie2OWR903pYsHI5HgcjJIJcTUMDNoAiIgCIiAIiIAiIgCIiAIicata2QzPCAbO4G2cbs2zIcTtPQTKUvSbbw3D7yr0np5ad0TvPs5L1M13kmFtszmKt6lbLM6lMazEDixP1lPjO0qLcUwWPE5Lf5medxGIesdZmJ5bh0EJRnNzc+n2haL0cNLvb2d6+l69TIvYcFsPja8hlGOZuepv85ZYfRzNay2HE2En09D+sx8P8yq/Wyed/wBm93jjskjz4o8psKU9Qmi6Y9G/Uzr+w0/VEyXFv3Zg+VPweS8kINGet/Yqfqic20bTPo26GP8Ai18hcqfg8maNsxt4jKSsPpOvTyDm3Bu8PjnLurodT5rEdbESvxGiXXPzhy+0joyw9oy9TFfZ/wCk7B9pRsqKQeK5j7y5RqdYXBDDiDsPzE8S9DjNKbvTbWRiDy39ZYxc6perRrycSa7x2PbNTenmp1l4bx952oYoPKbRnaENZKo1W2X3MfpLathw/eU2biNh6zpY805FuWc+8dQ9UibEr6GKIOo4s3z5jiJOBvNpgbREQBERAEREARE5VqmqIBrWqeiMyZoFCAsxztmTsA8dk2Qat2O3aeQnktOaXNYlENkBzPrn7TRnzLFOzbixO60jrpjTbOSlM2XYWG1unKVNGjx/+zpSocdssMJhdfkonDyZbzUdSZjFOkccLhS5so6mXGGwCpmczxt9J3pIFFgLToTLGLDM933ZWvLVGZnWml4vLPUajfWmdac7xeNkaOmtF5z1o1o6ho31o1pzvF5DrY0aYjDK+0eO+U2MwLLmMxyGzrL28EzVkia/JtjJUeDyFROUn6J0y1A6jXZOZzTpfdyknHYG3fUZbxwlbUpAiVVd4a7Fr9GWdM9kypWUEHbmGG0Hl9pyw+IKt5N9u4+sOInltF6SbDNY5oTmPV5ietqotZQQc8mVuE7PHzrLO/c5mbC8b/gmA3m0rsFiDmjZMMiJYyyaRERAEREA1YyPT7x1zsGz7zNc3so3/ASNpfGihTJG22qBz3TG7US6ZMy6aSKntHpP/ZU/iPyWU2FpWzmlJC5JOZJuSd5k5FtPPZ87y13OvjhY50bYejrECXNNQosJGwtPVHM/q0kXmzDPStmjJXUzpeY1pprRrTf1GvRvrRrTnrRrSOoaOmtM3nPWjWk9Q0b60a001o1o6ho31o1pzvF46ho6a0xeaXi8jY0dAZVY3DapuNh+BllrTSqoYWMwyJUjZDcvZ5+tSyk3QGkzTYUm80my+y32M1dLGxkDE094lfFkeO0yxczknTPY46kcqq7V2+0v3ElYWsHW4lboDSHlU1W85cjfeOM6Uz5Gpqei3eXlxHhPQY7WSVSORcuacstomoN5tNhiJqTYTaR8S2VhtOQ6mAa0c7ueg6fr5TyWnMUatbVBuqZfm3z1GkawpUmOyy2HW2U8XhEub/ozm8/LpKUXeJCe6fsTKSWElYenc8hOSiS8OLC85Ed6LdvsSLxOVSsqDWdgo4sQB7zKut2nwaGxxCX5Xb+0GXFt+CuXF4JlNR7T4NzqriEvz1l+LAS2WoGFwQQcwQbgjkRD2vIRteLzBMxMeonRteLzW8zeR1DRm8Xmt5m8dQ0ZvF5i8XjqGjN4vMXlZi+0GFpGz10B4XLEeC3mS2/A0Wl4vKRO1eCP/sJ4h1+ayzw2Mp1Rem6OPZYN8pOqINcWnpSG6XlnVF1MgWlXJrZvxvsRNHYjyNUNuJ1W6Get0lT1k1xtXvDpvHunkcZT3z0+gMT5SiATmO6fCdL6fl8yyvy47K0TsFV1lBkqVOj+470+DZfhOY+Etp1SgJFqZso4d73bJJkam3eY8AB87/IQCk7V1u6qX2tfwAlVhknXtBU1q1vVX5zNAZTz/NveVnVwz04kdbTznaLtctA+Rw4D1NjMT3aeXLzm5bp27Y6WOHo6qmz1Lop9Rbd5vjYczKHsp2TNYCvWJWmScs9eqeu5ee0yOPCS66/owqinWjicY928pXfgLlU8PNUe6XeH7EYph5iJyZ/sDPoVBEpKKdNAijYAINSbK5KXZGHS2fPMT2IxajJEfkr5/wBQEq8Nj8VoxxrJURPSRwQjD2dwPMT6wtQ8Zsa9xZgD1zmK5UeKHTRxoVg6K4uAyhwDtAYA2PPOdJziVqyrfYzSOl4vOcTH1SdHS8XE5xHqjR0vE5zN5PqjR867T6dxGIrvhKSuEVtQqgYvUYbS2rmFvumMF2Nxbi/kQg9tgp9wvPpNOpqg2AvxtnBqk7TLK5MpalGtzTZ8/qdhMVa//ablrn6rKTHaIxGFOsyPTI2OlwP51n1sPzmxe41TYjgQCDM55I6aR8/0F20dCKeK7ynIVcgVHtjf1E9irBu8pBBzBBBBB2WInnu0nY1agNTDgK3nGnufjqcDy2Sv7D6UZWbCPfK5S/o6t9ZPqPGY5pnJPVPsZRWmesxSZST2XqgM6cbMPr9JzrjuyNoptXELzuvwv9Jr4t9ORM3WlWNpno8X3KytudbeKn/IlqpylXpjYj8HA8CP8CWGHa6iejOQdTItLY55/SSjI1PY3U/KQweM0i2tXfkQPgJNprlIOL/fv+L6CWFPZPMcp6t/k7H7F+EUFbQwxeMNWqD5Ggqoo/iVT3iPwju345DcZ6VmueG4AbAOAmL5W/V5marzOkkvCNSn3YvMGZlbpLTmHw+VRwG9Ve8x8N01pVb0u5LZYibTxWI7eKP3eHLc3cLl0AM4J27qb8OtuTsD/bLC4mV+xHUe7MSg0V2soVyEa9NzkA9rMeAb72l/NFxUPVLRKexERNeyRERHUBETSrVVAWdgqjazEADxk92Nm8TzGO7a4enkitUPLur7zKxu3bnzcOvi5J+CyxPGytb0Yuke6ieNwnboE2q0Cg9ZG1reBAnqsFjErIKlNgyneNx4EbjMLxZI70gmmSJ5vtDoezpj6Is9N1eou56d7O2XpBSb8RPRiZVrfLqIxZqhhztHGoMpWobVUPtA/GWlTYZVN56/iHzmzDX/AGJr5Nsvsz12k1vR6FT8RJWCPcEh6TP/AI7dB8xO+jDdBPUrwch+SaZFpnJ+v0koyEgsXHQ/OGQePxotXfqPkJYU9ki6XXVrX9YD4ZSVhzcTy/NlrI/ydeXuE/4OoEzMzWolwRci4IuDYi4tkdxlPZizxvabtQyscNhrl9jOMyDvVBnmN5nlm0RiWGu1Gob5lipuT45z6lo/R1LDrq0kCcTtZurHMyZrGXJ5c41qJ/tmtps+MoliVYEMNoIII8DJy0gd0+h6d0MmLQg2Dgdx94bcDbas+dMHpMUqDVYZEH6cRzl3DyJyr4ZGjWvhxa8932N0i1ahZzdkbUudrDaL+BtPCVsQCLeE9z2M0e1GiWcWao2vY7QtrC/XbNfM6fT7+fYmT0MRE5BsEREbBX6Z0qmFpmo+e5VFru3AcuJnzvF18Vj219So637qopKKN1txPPbPpGI0XRqOtV11mUWUMSVXO9wuy/2k0Mf8bgOUt4s8YltLb/k10tnx6vgKlL95TdN3eUgX67JvQQEbJ9ddQwKsLg5EHMET592m0GcK/laak0m2jb5N+B9ngfCW8HMWR9LWmRrRUmiDukrs9jWw+IUA9x2FNl3G+Snre0iJiBa5IkvQWBbEYhNUXRGDs25QpuB4m0s5Onoe/BKPpswZkxOFszRzqbDKsi9RB7S/OWlU2ErsEutXQcCW9wMs8Zbtfk2LtLZ6fSP7gjiVX+oSXo9LIJA0ue4ij0nHuAv9pZYUWUT1aOQ/JIkJ8nHtAr9fpJsiY0ZBhuIb3G8kg852jp2KvwOr4H9fGcsI0uNMUPKIbbxcfMTzuAqbjOD9TxfqVL3Olx66sevgtomoMzOMzMRESAZkbF4ClWyqU0fhrAEjodokiJKbT2jHRAw2hMPTbXWioYbDa5HS+yWExEmrqvLGkIiJiSIiIJERMMwAJJAABJJ2ADMkwQzaZYAixAIO45g9ZWaI05RxeuKTE6lr6ylbg7GW+0ZSzmVTUPVLTI7Mqn7O4VjrGgl+QsPcJPw+HSmuqiKi8FAUfCdZiS8ltabGgYiJgSRsY1hNNA09aoz8LL79s44+pa8uOztCyA2zPePiBadb6dibtN+ERmrpxP8Ak7aQ71WmnqqWP5iAPkZcUxYCU2BPlKr1N2tqr+Fch8peCeiOYZmlRbgibxAKxM1KHap/pOyeVxtPydTkc/HfPW4ldRhU3bG/DKvTmE1lJAzHeEq8vD6mNpeUWOPk6a0/ci4Z7idpVYOvbI9JaK155XJLmtF6lozMzETWQZiYiCDMxEQBERAERMwRsxPOds9IhKX7Oub1RqkD0KXpM3C+wTOl+1dKnenRIq1NmWaIeLMNtuA+E8mqM7mpUYu7Zknjy4DlOhxuM9q77fBi2WfY7uYnVGxqb/0lLT3pniuylEnEFxsSm1zzdlAHwPuntbzXze9kyYmJmYlMyE0qPYTcyux1aZxO3oylbZxVTVcJuvc9J6XFP5Kibec1kUczl8BeVugsGR32GbZ9Fkqo3lqwAzSn3er7z9PCen4OD047+WU+Tk6n0rwix0Th9RBLGaU1sLTeXiqIiIByrJrAiVyDI0ztXMc14S2kLG0Ce8uTDMGAeV0phvJuaijunbyPGbYWvfKXVSmKinLMZMOE87isOaLXHmk5HgeBnF5/E79Uo6ODKrnpryWYM2kTDYi8lAzh1LT0zY1ozETKmDFkHF6Xw9Jir1UDDat7kHmBIFTtXhl9Nm/CjH6SFi+yRLu6VQA7F7MtyCxJOY6zh/0jU/ip/K33lyIwa7sx2ya/bGkPNpVW8EUfFpHftifRw5/O9v7QZoOyNT+Mg/K33nQdj39Kuvgh/wCU2JcZDuRK3avEN5qU05nWf6iVGLxmIr5VqzMvqLZE9y2v43np07HJ6Vd/yqo+YMkp2Tww85qj9XA/tAmSz4I+1f4DxaKqbgJJwdB62VJGfde1lHMscp7nD6Ew1PNaKk8W7x+Msb5WGQ5TG+av2oFZoTRS4ZCCdZ2Os7DedgA4ASxMTEoXbuupmSEEwZHr19WQk29IySbNcTiABtkTBYU1X1j5oPvPCaUkNV9UebvPCehpIlFNY5AZADax3AczOzwOHt9VGObIsc9K8szi63kl1V89+6vsje0l6Jwnk15yFgMO1RzVfadg9VdwEvlW07iRzW9m0REkgREQBMETMQCsxlAqddNu8esOBkStTSqtwOqnap/W+XjCVuKwZB1kyPwYcDIaTWmSm09o8tisO1E3Fyv9vWd8Niwcpc6q1Li1mG1Tv6cRKjGaNsdZMjvX7Tkcvgb3Ul/HyJrtZKVrzcSnTEMhswIk2jigZxcmKoejc50tkyYvNFqAze81aMdAzERAEREgGYias4ElLY0bTVmkepigBIVfFkmw6TZON09GSlkrEYoDf8ZEoUnrNlcLfNt3hJGG0az5vcDcu89eEtnKUlGtlwQbW6CdjicB/dRqvkTH6ZGHw6UkLHuqMyd7HlxM50Kb4hw7CyA91eA4niZmhhXrsHfIDYo2KPrL6jRCCwnZmVK0ihVOntszRphRYTrETIxEREAREQBERAEwRMxAIGMwIfMZHcRtBkB2Ze6639sbfzDf1l9Ob0w20QCgrYVKi3yYct32lVV0Wy5ob8jPSVtHWOshKnlv6yG5dfPS44rkfdNOXjxk8o3RmqfDPPF3Q95SPiPfOqY2XOuhy1rcmyz8Zo+jkbag6gfUTnZPpif2ssxyp/civXGjl8ZsMYOU6vohPaHQzl/o43M0rP6ZfwjP18LM/tYmr40fq8z/AKR7be6dF0Ou/XML6XfwT6+EiPjuc5+Ud8lBP64y4paMQbEHUi/znRnppkXXouZ9wlmPpaX3M11ypX2oqaOjnfzyFHAZn7S0w2CSmLgADexsMusyuIY5U0/M/wDxH3nRNGM5BqMW5bh0EvY+Ljx+EV8mer7bORxVzq0gWOzXPmj8I2kyVgtF567ksxzJOZljh8GqCwEkgSyaDWmgUWE3iIAiIgCIiAIiIAiIgCIiAIiIAmjIDum8QCHWwCNtEhtogDzWK9CRLiIBRnA1Rsc+IB+YnJsPiBsYeKiehmLQDz3kcR6y/wAonQYOswze3QAfIS9tFoBSLocnz3ZupMlUNFou4SyiAckoKN06ATMQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAREQD/2Q==");
            }
            else if (message.contains(smiles[2])){
                imageIcon =  new ImageIcon("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvgYraVPE33nDYQ7rUcIsCc9GDIxFCxV06Vg&usqp=CAU");
            }
            else if (message.contains(smiles[3])){
                imageIcon =  new ImageIcon("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd41k7RwzNVBgRYp_s-o8v2Hw2HwdYNTP3QQ&usqp=CAU");
            }
            else {
                imageIcon = null;
            }
            synchronized (messages) {
// Добавить в список сообщений новое
                messages.add(new ChatMessage(message, author,
                        Calendar.getInstance().getTimeInMillis(), imageIcon));
            }
        }
// Перенаправить пользователя на страницу с формой сообщения
        response.sendRedirect("/chat/compose_message.html");
    }

}
