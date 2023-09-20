import 'package:http/http.dart' as http;

class NetworkService {
  static const String _url = 'https://localhost:5050';
  static final NetworkService _instance = NetworkService._internal();

  factory NetworkService() {
    return _instance;
  }

  NetworkService._internal();

  Future<http.Response> getData(String endpoint) {
    var fullUrl = _url + endpoint;
    return http.get(Uri.parse(fullUrl));
  }

  Future<http.Response> postData(String endpoint, dynamic data) {
    var fullUrl = _url + endpoint;
    return http.post(Uri.parse(fullUrl), body : data);
  }
//...otros métodos para PUT, DELETE, etc.
}
