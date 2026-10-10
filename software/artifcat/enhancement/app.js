
const express = require('express');
const app = express();

const apiRouter = require('./app_api/routes/index');

// API routes
app.use('/api', apiRouter);

// Enhanced API error handling
app.use('/api', (err, req, res, next) => {
    console.error('API Error:', err.message);

    if (res.headersSent) {
        return next(err);
    }

    res.status(err.status || 500).json({
        message: 'An error occurred while processing the API request.',
        error: process.env.NODE_ENV === 'development'
            ? err.message
            : undefined
    });
});

// Handle unknown routes
app.use((req, res) => {
    res.status(404).send('Page not found');
});

module.exports = app;
