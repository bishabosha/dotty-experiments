package example

enum Maybe[+A] {
  case Just(value: A)
  case Nothing
}