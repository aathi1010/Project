const db = require("../db");

// ======================
// CREATE TASK
// ======================
exports.createTask = async (req, res) => {
  const { title } = req.body;
  const { id, org_id } = req.user;

  try {
    // 1. Insert task
    const result = await db.query(
      "INSERT INTO tasks (title, org_id, created_by) VALUES ($1,$2,$3) RETURNING *",
      [title, org_id, id]
    );

    const newTask = result.rows[0];

    // 2. Insert audit log
    await db.query(
      "INSERT INTO audit_logs (action, user_id, org_id, task_id) VALUES ($1,$2,$3,$4)",
      ["CREATE_TASK", id, org_id, newTask.id]
    );

    // 3. Send response
    res.json(newTask);
  } catch (err) {
    res.status(500).json(err.message);
  }
};

// ======================
// GET TASKS (MULTI-TENANT)
// ======================
exports.getTasks = async (req, res) => {
  const { org_id } = req.user;

  try {
    const result = await db.query(
      "SELECT * FROM tasks WHERE org_id = $1 ORDER BY created_at DESC",
      [org_id]
    );

    res.json(result.rows);
  } catch (err) {
    res.status(500).json(err.message);
  }
};

// ======================
// UPDATE TASK (RBAC)
// ======================
exports.updateTask = async (req, res) => {
  const { id: taskId } = req.params;
  const { status } = req.body;
  const { id: userId, org_id, role } = req.user;

  try {
    let query;
    let params;

    // Admin → update any task
    if (role === "admin") {
      query =
        "UPDATE tasks SET status=$1 WHERE id=$2 AND org_id=$3 RETURNING *";
      params = [status, taskId, org_id];
    } else {
      // Member → only own task
      query =
        "UPDATE tasks SET status=$1 WHERE id=$2 AND org_id=$3 AND created_by=$4 RETURNING *";
      params = [status, taskId, org_id, userId];
    }

    const result = await db.query(query, params);

    if (result.rows.length === 0) {
      return res.status(403).json({ msg: "Not allowed" });
    }

    const updatedTask = result.rows[0];

    // Audit log
    await db.query(
      "INSERT INTO audit_logs (action, user_id, org_id, task_id) VALUES ($1,$2,$3,$4)",
      ["UPDATE_TASK", userId, org_id, taskId]
    );

    res.json(updatedTask);
  } catch (err) {
    res.status(500).json(err.message);
  }
};

// ======================
// DELETE TASK (OPTIONAL BONUS)
// ======================
exports.deleteTask = async (req, res) => {
  const { id: taskId } = req.params;
  const { id: userId, org_id, role } = req.user;

  try {
    let query;
    let params;

    if (role === "admin") {
      query = "DELETE FROM tasks WHERE id=$1 AND org_id=$2 RETURNING *";
      params = [taskId, org_id];
    } else {
      query =
        "DELETE FROM tasks WHERE id=$1 AND org_id=$2 AND created_by=$3 RETURNING *";
      params = [taskId, org_id, userId];
    }

    const result = await db.query(query, params);

    if (result.rows.length === 0) {
      return res.status(403).json({ msg: "Not allowed" });
    }

    // Audit log
    await db.query(
      "INSERT INTO audit_logs (action, user_id, org_id, task_id) VALUES ($1,$2,$3,$4)",
      ["DELETE_TASK", userId, org_id, taskId]
    );

    res.json({ msg: "Task deleted successfully" });
  } catch (err) {
    res.status(500).json(err.message);
  }
};