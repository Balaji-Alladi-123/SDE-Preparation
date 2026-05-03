
class Use {
    name:string;

class User {
  constructor(public name: string, public age: number) {}
function User(name: string, age: number) {
    this.name = name;
    this.age = age;
  }
  
  User.prototype.greet = function () {
    console.log(`Hello, my name is ${this.name}`);
  };
}
  const user2 = new User("Bob", 30);
  user2.greet(); // Hello, my name is Bob
  