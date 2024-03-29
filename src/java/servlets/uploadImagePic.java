/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.handleFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import classes.user;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Lenovo
 */

@MultipartConfig

public class uploadImagePic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // update profile image
        HttpSession session = ((HttpServletRequest) request).getSession();
        user curUser = (user) session.getAttribute("user");
        Part filePart = request.getPart("image"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        handleFile.deleteIMG(curUser.getPicname());
        handleFile.updateIMG(curUser.getPicname(), fileContent);
        response.sendRedirect("login.jsp");
    }
}
