## what is lychee?
Lychee is a paint app designed to use code instead of brush.
Lychee uses Zest, our custom language, to design images in code. This has many advantages:
- smaller image files
- more shareability
- more friendship
- more zestiness
  You may be asking: why not just use SVG? Our answer: embrace the zest. Live the wild life. Live the lychee life.
## why is lychee?
Lychee is useful for a few different applications:
- simple graphic design work
- learning to code
- providing a more intuitive alternative to svg
## commands
### line
Draws a straight line from the start to the end, using the provided width.
```js
line([0, 0], [100, 100], 5);
```

parameters:
- `start`: `Coordinate`
- `end`: `Coordinate`
- `width`: `int`

returns:
- `Shape`
### erase
Erases in a straight line from start to end, using the provided width.
```js
erase([0, 0], [100, 100], 5);
```

parameters:
- `start`: `Coordinate`
- `end`: `Coordinate`
- `width`: `int`

returns:
- `Shape`
### fill
Fills a shape with the currently set colour.
```js
var shape1 = line([0, 0], [100, 100], 5);
fill(shape1);
```

parameters:
- `shape`: `Shape`

### circle
```js
line([0, 0], [100, 100], 5);
```

parameters:
- `start`: `Coordinate`
- `end`: `Coordinate`
- `width`: `int`

returns:
- `Shape`

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