'use client'

import { useState } from "react";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

export default function LoginPage() {
  const router = useRouter();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:3333/auth/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username: email, password }),
      });

      const token = await response.text();

      if (response.ok) {
        // Save token in cookies
        Cookies.set("auth-token", token, { expires: 7 });

        alert("Login successful!");
        router.push("/movies"); 
      } else {
        alert("Login failed.");
      }
    } catch (err) {
      console.error("Login error:", err);
      alert("Something went wrong.");
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 border shadow rounded">
      <h1 className="text-2xl font-bold mb-4 text-center">Login</h1>
      <form onSubmit={handleLogin}>
        <div className="mb-4">
          <label className="block mb-1">Email</label>
          <input
            type="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full border p-2 rounded"
            placeholder="admin@example.com"
          />
        </div>
        <div className="mb-4">
          <label className="block mb-1">Password</label>
          <input
            type="password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full border p-2 rounded"
            placeholder="••••••••"
          />
        </div>
        <button
          type="submit"
          className="w-full bg-red-500  py-2 rounded hover:bg-red-700"
        >
          Login
        </button>
      </form>
    </div>
  );
}
