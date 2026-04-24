require("dotenv").config();
const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

const taskRoutes = require("./routes/taskRoutes");
const authRoutes = require("./routes/authRoutes");

app.use("/tasks", taskRoutes);
app.use("/auth", authRoutes);

app.listen(process.env.PORT, () => {
  console.log("Server running on port", process.env.PORT);
});