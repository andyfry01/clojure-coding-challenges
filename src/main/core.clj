(ns main.core
  (:require [testmodule])
  (:gen-class))

(def corestring "Hello from the core module!")

(println testmodule/tstring)