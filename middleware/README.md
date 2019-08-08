# bpm-middleware

A middleware written in Express.js. It forwards requests and responses between front-end in React and Activiti REST API in Java, as well as handle authentication using JSON Web Token (JWT). 

## Setup

- Install dependencies

```
npm install --save .
```

- Add environment variables in `.env`

```
SECRET=<secret>
ADMIN_ID=admin
```

- Start the APIs

```
npm start
```