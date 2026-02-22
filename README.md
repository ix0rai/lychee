## What is Lychee?
Lychee is a paint app designed to use code to draw images instead of brushstrokes.
Lychee uses Zest, our custom programming language, to draw images. This has many advantages:
- Smaller image files
- More shareability
- More friendship
- More zestiness

You may be asking: why not just use SVG? Our answer: embrace the zest. Live the wild life. Live the Lychee life.
## Why is Lychee?
Lychee is useful for a few different applications:
- Simple graphic design work
- Learning to code
- Providing a more intuitive alternative to SVG

## Usage
Run using Gradle:
```bash
./gradlew run
```

## Commands
### line
Draws a straight line from the starting coordinates to the ending coordinates, using the provided width. Can be colored.
```js
line([0:0], [100:100], 5, "red");
line([0:0], [100:100], 5, "#bf3344");
```

Parameters:
- `start`: `Coordinate` 
- `end`: `Coordinate`
- `width`: `int`
- `color`: `String`

### erase
Erases a straight line from the starting coordinates to the ending coordinates, using the provided width. Cannot be colored.
```js
erase([0:0], [100:100], 5);
```

Parameters:
- `start`: `Coordinate`
- `end`: `Coordinate`
- `width`: `int`

### fill
Fills a rect with the chosen color from the starting coordinates, using the provided height and width.
```js
fill([0:0], 50, 100, "red");
fill([0:0], 50, 100, "#bf3344");
```

Parameters:
- `start`: `Coordinate`
- `width`: `int`
- `height`: `int`
- `color`: `String` 

### circle
Draws a circle from the starting coordinates, using the provided height and width. Can be colored.
```js
circle([0:0], 30, 30, "red");
circle([0:0], 30, 30, "#bf3344");
```

Parameters:
- `start`: `Coordinate`
- `width`: `int`
- `height`: `int`
- `color`: `String` 

### text
Draws text from the starting coordinates, using the provided font size. Can be colored.
```js
text([50:50], 30, "hello", "red");
text([50:50], 30, "hello", "#bf3344");
```

Parameters:
- `start`: `Coordinate`
- `fontSize`: `int`
- `text`: `String`
- `color`: `String`

### layer
Creates a layer. Commands in higher layers will be drawn over lower layers.
Any command not inside a layer block will be drawn on layer `0`.
```js
layer(1) {
    //commands go here
}
```

Parameters:
- `layer`: `int`

## Notes
- A color can be written as a word or a hexcode, but words have a limited selection of colors (black, white, red, orange, yellow, green, blue, purple, pink)
- Coordinates are in `[x:y]` format
- Zest supports comments on lines beginning with `//`
