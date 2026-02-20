- paint app
    - write code to paint
    - paint window | fill(1,2,3)
    - export image:
        - system file chooser using flatlaf?

export image:
```java
BufferedImage image = new BufferedImage(panel.getSize().width, panel.getSize().height, BufferedImage.TYPE_INT_RGB);
panel.printAll(image.getGraphics());
```

- infinite layers
- commands all return a shape, which can then be passed into fill
- color?
- syntax highlighting (jflex????? syntaxpain??)
- live reload as you type (keyboard listener)
- color picker (low priority)
- implement loops in zest (low priority)
- show coordinates of mouse in the corner


```
set color(hex code, gradient?)

layer 1 {
	line (start, end, width) -> shape
	erase (start, end, width) -> shape
	fill (shape) -> void
	
	set color
}

// between layers, color is cleared
// layers with higher numbers are above lower ones

layer 2 {
	circle (radius, x, y, line width) -> shape
	square (length, x, y) -> shape
	paint (image) -> shape
	text (string) -> shape
	curve (start, end, middle, width) 
}

types:
coordinate: [x, y]
int: i
string: ""
image: </path/to/image.png>

```

```js
1 {
	line([0, 0], [100, 100], 5);
	erase([40, 40], [60, 60], 5);
}
```

line [0, 0], [100, 100] 5
switch (name) {
    casse "line" {
        new LineCommand(parameters)
        list.add(command)

```java
class Command {
	public void execute(Parameters params) {
		// run code
	}
}

class FillCommand {
	FillCommand(color color) {
		this.color = color;
	}

	public void execute(Parameters params) {
		// fill
	}
}

class Parameters {
	private Coordinate start;
	private Coordinate end;
	private String text;
	private int radius;
	private int width;
}
```