const express = require('express');
const app = express();
const port = process.env.PORT || 3000;

app.get('/', (req, res) => {
  res.json({ message: 'Hello from sample-app!' });
});

app.listen(port, "0.0.0.0", () => {
  console.log(`Server listening on ${port}`);
});

