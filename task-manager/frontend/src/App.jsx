import { useEffect, useState } from "react";

const API = "http://localhost:5000/tasks";

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");

  //  Load tasks on page load
  useEffect(() => {
    fetchTasks();
  }, []);

  // 📥 GET TASKS
  const fetchTasks = async () => {
    try {
      const res = await fetch(API, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });

      const data = await res.json();
      setTasks(data);
    } catch (err) {
      console.error("Error fetching tasks:", err);
    }
  };

  // ➕ CREATE TASK
  const addTask = async () => {
    if (!title.trim()) return;

    try {
      await fetch(API, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify({ title }),
      });

      setTitle("");
      fetchTasks();
    } catch (err) {
      console.error("Error adding task:", err);
    }
  };

  // ✏️ UPDATE TASK STATUS
  const updateTask = async (id, status) => {
    try {
      await fetch(`${API}/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify({
          status: status === "pending" ? "completed" : "pending",
        }),
      });

      fetchTasks();
    } catch (err) {
      console.error("Error updating task:", err);
    }
  };

  // ❌ DELETE TASK
  const deleteTask = async (id) => {
    try {
      await fetch(`${API}/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });

      fetchTasks();
    } catch (err) {
      console.error("Error deleting task:", err);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 to-gray-900 text-white flex items-center justify-center">
      <div className="w-full max-w-xl p-6">

        {/* 🔥 TITLE */}
        <h1 className="text-3xl font-bold text-center mb-6">
          Task Manager 🚀
        </h1>

        {/* ➕ INPUT */}
        <div className="flex gap-2">
          <input
            type="text"
            placeholder="Enter task..."
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="flex-1 px-4 py-2 rounded bg-gray-800 border border-gray-700 focus:outline-none"
          />
          <button
            onClick={addTask}
            className="bg-blue-500 hover:bg-blue-600 px-4 py-2 rounded"
          >
            Add
          </button>
        </div>

        {/* 📋 TASK LIST */}
        <div className="mt-6 space-y-3">
          {tasks.length === 0 ? (
            <p className="text-gray-400 text-center">
              No tasks yet...
            </p>
          ) : (
            tasks.map((task) => (
              <div
                key={task.id}
                className="bg-gray-800 p-4 rounded flex justify-between items-center"
              >
                {/* LEFT */}
                <div>
                  <p
                    className={`text-lg ${
                      task.status === "completed"
                        ? "line-through text-gray-400"
                        : ""
                    }`}
                  >
                    {task.title}
                  </p>
                  <p className="text-xs text-gray-500">
                    {task.status}
                  </p>
                </div>

                {/* RIGHT ACTIONS */}
                <div className="flex gap-2">
                  <button
                    onClick={() => updateTask(task.id, task.status)}
                    className="text-sm bg-green-500 px-2 py-1 rounded hover:bg-green-600"
                  >
                    Toggle
                  </button>

                  <button
                    onClick={() => deleteTask(task.id)}
                    className="text-sm bg-red-500 px-2 py-1 rounded hover:bg-red-600"
                  >
                    Delete
                  </button>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default App;