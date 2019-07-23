<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"
         errorPage="/infrostructure/Errors.jsp"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>EMPApp Login</title>
    <link type="text/css" rel="stylesheet" href="../css/jdeveloper.css"/>
  </head>
  <body><h1>
  Login to SRDEMO
</h1>
<form action="j_security_check" method="post">
      <table cellspacing="2" cellpadding="3" border="0">
    <tr>
      <td>Username</td>
      <td>
        <input type="text" name="j_username" size="30"/>
      </td>
    </tr>
    <tr>
      <td>Password</td>
      <td>
        <input type="password" name="j_password" size="30"/>
      </td>
    </tr>
  </table>
      <p>
    <input type="submit" name="logon" value="Sign On"/>
  </p>
      <p>
        &nbsp;
      </p>
    </form>
</body>
</html>