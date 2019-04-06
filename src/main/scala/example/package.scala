package example

def (value1: A) `min` [A: Ordered] (value2: A): A =
    if value1 <= value2 then value1 else value2

def minimum [A: Ordered] (vals: A*): A =
  vals.reduce(_ `min` _)