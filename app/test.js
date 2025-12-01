const http = require("http");

const host = process.argv[2] || "127.0.0.1";
const port = 3000;

const options = {
  hostname: host,
  port: port,
  path: "/",
  method: "GET",
};

console.log("Testing:", host + ":" + port);

const req = http.request(options, (res) => {
  let data = "";
  res.on("data", (chunk) => data += chunk);
  res.on("end", () => {
    try {
      const json = JSON.parse(data);
      if (json.message?.includes("Hello")) {
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

req.on("error", (err) => {
  console.error("TEST FAILED:", err);
  process.exit(1);
});

req.end();

