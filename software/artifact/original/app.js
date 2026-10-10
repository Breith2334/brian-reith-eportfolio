const express = require('express');
const app = express();

const apiRouter = require('./app_api/routes/index');

// API routes
app.use('/api', apiRouter);

// Handle unknown routes
app.use((req, res) => {
    res.status(404).send('Page not found');
});

module.exports = app;
