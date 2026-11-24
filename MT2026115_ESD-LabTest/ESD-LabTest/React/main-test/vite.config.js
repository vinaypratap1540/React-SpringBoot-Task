import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// npm run dev → the app on http://localhost:5173
// npm test    → the five task tests in test/
export default defineConfig({
  plugins: [react()],
  server: { port: 5173, strictPort: true },
  test: {
    environment: "jsdom",
    setupFiles: ["./test/setup.js"],
    include: ["test/**/*.test.{js,jsx}"],
    restoreMocks: true,
  },
});
