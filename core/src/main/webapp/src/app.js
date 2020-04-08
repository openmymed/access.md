import { el, text, mount } from 'redom';
class A{
  constructor(attr, text) {
    <div this="el">
      <h3>{attr.title}</h3>
      <span this="span">Hello DOCKERY BOIS AND GIRLS</span> {text}
      <h4>{attr.name}</h4>
    </div>;
  }
  update(data){
    this.span.textContent = data;
  }
}

class B{
  constructor() {
    <div this="el">
      <A this="a" title="Hello World example" name="A VERY TESTY BOI">
        <span this="span">World</span>
      </A>
    </div>;
  }
  update() {
    this.span.textContent = "You";
    this.a.update("test");
  }
}

const b = <B />;

mount(document.body, b);

b.update();
