const db = require("../db");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");

// SIGNUP
exports.signup = async (req, res) => {
  const { name, email, password, org_id, role } = req.body;

  try {
    const hashedPassword = await bcrypt.hash(password, 10);

    const result = await db.query(
      "INSERT INTO users (name, email, password, org_id, role) VALUES ($1,$2,$3,$4,$5) RETURNING *",
      [name, email, hashedPassword, org_id, role || "member"]
    );

    res.json(result.rows[0]);
  } catch (err) {
    res.status(500).json(err.message);
  }
};

// LOGIN
exports.login = async (req, res) => {
  const { email, password } = req.body;

  try {
    const user = await db.query(
      "SELECT * FROM users WHERE email=$1",
      [email]
    );

    if (user.rows.length === 0)
      return res.status(400).json({ msg: "User not found" });

    const valid = await bcrypt.compare(password, user.rows[0].password);

    if (!valid)
      return res.status(400).json({ msg: "Invalid password" });

    const token = jwt.sign(
      {
        id: user.rows[0].id,
        org_id: user.rows[0].org_id,
        role: user.rows[0].role,
      },
      process.env.JWT_SECRET,
      { expiresIn: "1h" }
    );

    res.json({ token });
  } catch (err) {
    res.status(500).json(err.message);
  }
};