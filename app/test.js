const http = require("http");

const options = {
  hostname: "localhost",
  port: 3000,
  path: "/",
  method: "GET",
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

req.on("error", (err) => {
  console.error("TEST FAILED:", err);
  process.exit(1);
});

req.end();

