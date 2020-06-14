export default class UnifiedRecipe {
  constructor(recipe) {
    const { title, summary, extendedIngredients,
      readyInMinutes, analyzedInstructions, image, id, servings } = recipe;
    this.recipeid = id;
    this.name = title;
    this.description = summary;
    this.ingredients = extendedIngredients;
    this.readyInMinutes = readyInMinutes;
    this.steps =
      analyzedInstructions[0].steps
          .map((step) =>
            ({
              stepdescription: step.step,
              time: readyInMinutes / analyzedInstructions[0].steps.length
            }));
    this.photos = [image];
    this.servings = servings;
  }
}
