API errors are logged for troubleshooting.

API failures return a structured JSON response.

Development mode can display useful error details.

Production responses avoid exposing internal error messages.

Requests with an already-started response are passed to Express's next error handler
