import React from "react";
import "../../App.css";
import Footer from "../Footer";
import { Button } from "../Button";
import "../HeroSection.css";
import "./Products.css";

function Products() {
  return (
    <>
      <link
        href="https://unpkg.com/tailwindcss/dist/tailwind.min.css"
        rel="stylesheet"
      />

      <div class="w-full flex flex-wrap">
        <div class="w-1/2 shadow-2xl">
          <img
            class="object-cover w-full h-screen hidden md:block"
            src="/images/img-med.jpg"
          />
        </div>

        <div class="w-full md:w-1/2 flex flex-col">
          <div class="flex flex-col justify-center md:justify-start my-auto pt-8 md:pt-0 px-8 md:px-24 lg:px-32">
            <p class="text-center text-3xl">Welcome</p>
            <form
              class="flex flex-col pt-3 md:pt-8"
              onsubmit="event.preventDefault();"
            >
              <div class="flex flex-col pt-4">
                <label for="email" class="text-lg">
                  Email
                </label>
                <input
                  type="email"
                  id="email"
                  placeholder="your@email.com"
                  class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mt-1 leading-tight focus:outline-none focus:shadow-outline"
                />
              </div>

              <div class="flex flex-col pt-4">
                <label for="password" class="text-lg">
                  Password
                </label>
                <input
                  type="password"
                  id="password"
                  placeholder="Password"
                  class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mt-1 leading-tight focus:outline-none focus:shadow-outline"
                />
              </div>

              <div class="flex flex-col pt-10"></div>

              <div className=".login-btns">
                <Button
                  className="btns"
                  buttonStyle="btn--outline2"
                  buttonSize="btn--medium"
                >
                  Log In
                </Button>
              </div>
            </form>
            <div class="text-center pt-12 pb-12">
              <p>
                Don't have an account?{" "}
                <a href="/sign-up" class="underline font-semibold">
                  Sign up.
                </a>
              </p>
            </div>
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
}

export default Products;
