function demo(arr: number[]): number[] {
  let nums: number[] = [];
  console.log(arr.slice(3, 6));
  return nums;
}

function show(str: string): Map<string, number> {
  let map: Map<string, number> = new Map();
  for (let i = 0; i < str.length; i++) {
    if (!map.has(str.charAt(i))) {
      map.set(str.charAt(i), 1);
    } else {
      map.set(str.charAt(i), (map.get(str.charAt(i)) || 0) + 1);
    }
  }

  let result: Map<string, number> = new Map();
  let keys = map.entries();
  for (let [key, value] of keys) {
    result.set(key, value);
  }
  console.log(map.delete("l"));
  return result;
}
// const res = demo([1, 2, 3, 347, 34, 23, 789, 56, 8990]);
// console.log(res);

const original = {
  name: "Alice",
  age: 25,
  address: {
    city: "New York",
    zip: 10001
  }
};

// Creating a shallow copy using Object.assign()
const shallowCopy = original;

// Modifying the nested object in the copy
shallowCopy.address.city = "Los Angeles";

console.log(original.address.city); // "Los Angeles" (affected!)
console.log(shallowCopy.address.city); // "Los Angeles"
