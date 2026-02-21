export interface ItemDetail {
    id: number;
    name: string;
    description: string;
    cost: number;
    unit: string;
    imagePath: string;
    imageDescription: string;
    inStock: number;
}

export const mockItems: ItemDetail[] = [
    {
        id: 1,
        name: "Premium Wireless Headphones",
        description: "High-quality noise-canceling wireless headphones with up to 30 hours of battery life. Perfect for travel or focused work.",
        cost: 250,
        unit: "$",
        imagePath: "../assets/logo.png",
        imageDescription: "Black over-ear wireless headphones",
        inStock: 20
    },
    {
        id: 2,
        name: "Ergonomic Office Chair",
        description: "Comfortable ergonomic chair with lumbar support, adjustable height, and breathable mesh back. Ideal for long work sessions.",
        cost: 180,
        unit: "$",
        imagePath: "../assets/logo.png",
        imageDescription: "Black mesh office chair",
        inStock: 5
    },
    {
        id: 3,
        name: "Mechanical Keyboard",
        description: "Tactile mechanical keyboard with RGB backlighting and customizable switches. Enhances typing speed and gaming performance.",
        cost: 120,
        unit: "$",
        imagePath: "../assets/logo.png",
        imageDescription: "RGB mechanical keyboard",
        inStock: 15
    },
    {
        id: 4,
        name: "4K Monitor 27-inch",
        description: "Ultra HD 4K monitor with vibrant colors, fast response time, and ultra-thin bezels. Great for design work and gaming.",
        cost: 320,
        unit: "$",
        imagePath: "../assets/logo.png",
        imageDescription: "27-inch 4K monitor showing a landscape",
        inStock: 0
    }
];
