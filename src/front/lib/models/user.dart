import 'package:objectid/objectid.dart';

class User {
  final ObjectId id;
  final String username;
  final String firstName;
  final String lastName;
  final String email;
  final String password;
  final String mobile;

  User({
    required this.id,
    required this.username,
    required this.firstName,
    required this.lastName,
    required this.email,
    required this.password,
    required this.mobile,
});

  factory User.fromMap(Map userMap){
    return User(
      id: userMap['id'],
      username: userMap['username'],
      firstName: userMap['firstName'],
      lastName: userMap['lastName'],
      email: userMap['email'],
      password: userMap['password'],
      mobile: userMap['mobile'],
    );
  }

}