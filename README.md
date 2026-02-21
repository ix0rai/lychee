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
## Commands
### line
Draws a straight line from the starting coordinates to the ending coordinates, using the provided width. Can be colored.
```js
line([0, 0], [100, 100], 5);
line([0, 0], [100, 100], 5), "white";
line([0, 0], [100, 100], 5), "#FFFFFF";
```

parameters:
- `start`: `Coordinate` 
- `end`: `Coordinate`
- `width`: `int`
- `color`: `String` (optional)

### erase
Erases a straight line from the starting coordinates to the ending coordinates, using the provided width. Cannot be colored.
```js
erase([0, 0], [100, 100], 5);
```

parameters:
- `start`: `Coordinate`
- `end`: `Coordinate`
- `width`: `int`

### fill
Fills a rect with the current or chosen color from the starting coordinates, using the provided height and width.
```js
fill([0, 0], 50, 100);
fill([0, 0], 50, 100, "white");
fill([0, 0], 50, 100, "#FFFFFF");
```

parameters:
- `start`: `Coordinate`
- `width`: `int`
- `height`: `int`
- `color`: `String` (optional)

### circle
Draws a circle from the starting coordinates, using the provided height and width. Can be colored.
```js
circle([0:0], 30, 30);
circle([0:0], 30, 30, "white");
circle([0:0], 30, 30, "#FFFFFF");
```

parameters:
- `start`: `Coordinate`
- `width`: `int`
- `height`: `int`
- `color`: `String` (optional)

## Notes
- The default color is black. Using a command and specifying a color makes it the current color
- A color can be written as a word or a hexcode, but words have a limited selection of colors (black, white, red, orange, yellow, green, blue, purple, pink)
- Coordinates are in `[x:y]` format

PLANS FOR TOMORROW:

j
- [ ] fix code panel resizing itself (adding scrolling)
- [ ] implement error handling
- [ ] implement layers

t
- [ ] make examples
- [ ] write documentation
- [ ] new logo (made using zest)
- [ ] improve syntax highlighting