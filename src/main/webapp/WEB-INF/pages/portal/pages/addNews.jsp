<%-- 
    Document   : vest
    Created on : Apr 26, 2021, 11:35:51 PM
    Author     : Bojan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Write A Comment</h2>
        <form th:action="/login" method="post">
          <div class="one_third first">
            <label for="username">Korisnicko ime <span>*</span></label>
            <input type="text" name="name" id="name" value="" size="22" required>
          </div>
          <div class="one_third">
            <label for="email">Mail <span>*</span></label>
            <input type="email" name="email" id="email" value="" size="22" required>
          </div>
          <div class="one_third">
            <label for="url">Website</label>
            <input type="url" name="url" id="url" value="" size="22">
          </div>
          <div class="block clear">
            <label for="comment">Your Comment</label>
            <textarea name="comment" id="comment" cols="25" rows="10"></textarea>
          </div>
          <div>
            <input type="submit" name="submit" value="Submit Form">
            &nbsp;
            <input type="reset" name="reset" value="Reset Form">
          </div>
        </form>
