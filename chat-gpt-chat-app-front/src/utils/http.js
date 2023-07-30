import axios from 'axios';

const httpClient = axios.create();

export const http = {
  get: function get(url) {
    return httpClient.get(url).then(res => res.data);
  },
  post: function post(url, body) {
    return httpClient.post(url, body).then(res => res.data);
  },
}