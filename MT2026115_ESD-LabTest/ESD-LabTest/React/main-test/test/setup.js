// Runs before every test file: adds matchers like toBeInTheDocument(), and unmounts
// whatever a test rendered so tests can't see each other's screens.
import "@testing-library/jest-dom/vitest";
import { afterEach } from "vitest";
import { cleanup } from "@testing-library/react";

afterEach(() => cleanup());
