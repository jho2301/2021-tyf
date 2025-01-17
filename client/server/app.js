import express from 'express';
import path from 'path';
import http from 'http';
import https from 'https';
import fs from 'fs';

const app = express();

app.use((req, res, next) => {
  req.protocol === 'https' ? next() : res.redirect('https://' + req.headers.host + req.url);
});

app.use('/', express.static(path.resolve('dist')));

app.get('/*', (req, res) => {
  res.sendFile(path.resolve('dist', 'index.html'));
});

const httpsOption = {
  cert: fs.readFileSync('/etc/ssl/domain.cert.pem'),
  key: fs.readFileSync('/etc/ssl/private.key.pem'),
};

http.createServer(app).listen(80, () => {
  console.log('server is running on port 80');
});

https.createServer(httpsOption, app).listen(443, () => {
  console.log('server is running on port 443');
});
