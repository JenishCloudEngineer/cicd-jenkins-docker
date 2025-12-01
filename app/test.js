const http = require("http");

const host = process.argv[2] || "localhost";
const port = 3000;

console.log(`Testing: ${host}:${port}`);

const options = {
  hostname: host,
  port: port,
  path: "/",
  method: "GET",
  timeout: 5000
};

const req = http.request(options, (res) => {
  let data = "";
  res.on("data", (chunk) => (data += chunk));
  res.on("end", () => {
    try {
      const json = JSON.parse(data);
      if (json.message && json.message.includes("Hello")) {
        console.log("TEST PASSED");
        process.exit(0);
      } else {
        console.error("TEST FAILED: Wrong response");
        process.exit(1);
      }
    } catch (err) {
      console.error("TEST FAILED: Invalid JSON");
      process.exit(1);
    }
  });
});

req.on("timeout", () => {
  console.error("TEST FAILED: Request timed out");
  req.destroy();
  process.exit(1);
});

req.on("error", (err) => {
  console.error("TEST FAILED:", err.message);
  process.exit(1);
});

req.end();

