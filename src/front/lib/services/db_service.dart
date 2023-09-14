import 'dart:convert';
import 'dart:ffi';

import 'package:bcrypt/bcrypt.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:front/models/user.dart';
import 'package:front/services/globals.dart';

class DatabaseServices {
  static Future<User> addUser (String username, String firstName,
      String lastName, String email, String password, String mobile) async {

    const int rounds = 15;
    final salt = BCrypt.gensalt(logRounds: rounds);
    String encPass = BCrypt.hashpw(password, salt);

    Map user = {
      "username": username,
      "firstName": firstName,
      "lastName": lastName,
      "email": email,
      "password": encPass,
      "mobile": mobile
  };

    var body = jsonEncode(user);
    var url = Uri.parse("${baseUrl}noAuth/signUp");

    http.Response response = await http.post(url,
      body: body,
      headers: headers
    );

    print(response);
    Map responseMap = jsonDecode(response.body);
    User newUser = User.fromMap(responseMap);

    return newUser;

  }
}