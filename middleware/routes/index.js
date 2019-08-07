require('dotenv').config();
const express = require('express');
const request = require('request');
const jwt = require('jsonwebtoken');
const router = express.Router();
const {auth} = require('./auth');

router.post('/login', (req, res, next) => {
  const {id, password} = req.body;
  request.get({
    url: 'http://localhost:8080/auth',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: {
      'userId': id,
      'password': password
    },
    json: true
  }, (error, response, body) => {
    if (Boolean(body)) {
      res.send({
        id,
        token: jwt.sign({id}, process.env.SECRET),
      });
    } else {
      res.sendStatus(400);
    }
  });
});

router.post('/start', function (req, res, next) {
  request.post({
    url: 'http://localhost:8080/start',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: req.body,
    json: true
  }, (error, response, body) => {
    res.sendStatus(response.statusCode);
  });
});

router.get('/pending', auth.required, function (req, res, next) {
  request.get({
    url: 'http://localhost:8080/pending',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: {
      'userId': req.payload.id
    },
    json: true
  }, (error, response, body) => {
    res.send(body);
  });
});

router.post('/claim', auth.required, function (req, res, next) {
  request.post({
    url: 'http://localhost:8080/claim',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: {
      'userId': req.payload.id,
      'taskId': req.body.taskId
    },
    json: true
  }, (error, response, body) => {
    res.sendStatus(response.statusCode);
  });
});

router.get('/processing', auth.required, function (req, res, next) {
  request.get({
    url: 'http://localhost:8080/processing',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: {
      'userId': req.payload.id
    },
    json: true
  }, (error, response, body) => {
    res.send(body);
  });
});

router.get('/task', auth.required, function (req, res, next) {
  request.get({
    url: 'http://localhost:8080/task',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: req.query,
    json: true
  }, (error, response, body) => {
    res.send(body);
  });
});

router.post('/complete', auth.required, function (req, res, next) {
  request.post({
    url: 'http://localhost:8080/complete',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: req.body,
    json: true
  }, (error, response, body) => {
    res.sendStatus(response.statusCode);
  });
});

router.get('/tasks', auth.required, auth.admin, function (req, res, next) {
  request.get({
    url: 'http://localhost:8080/tasks',
    headers: {
      'Authorization': 'Basic YWRtaW46YWRtaW4='
    },
    body: req.query,
    json: true
  }, (error, response, body) => {
    res.send(body);
  });
});

router.options("/*", function (req, res, next) {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
  res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With');
  res.send(200);
});

module.exports = router;
