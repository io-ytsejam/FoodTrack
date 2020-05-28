export default class UnifiedRecipe {
  constructor(recipe) {
    const { title, summary, extendedIngredients,
      readyInMinutes, analyzedInstructions, image } = recipe;
    this.name = title;
    this.description = summary;
    this.ingedients = extendedIngredients;
    this.readyInMinutes = readyInMinutes;
    this.steps =
      analyzedInstructions[0].steps
          .map((step) =>
            ({
              stepdescription: step.step,
              time: readyInMinutes / analyzedInstructions[0].steps.length
            }));
    this.photos = [image];
  }
}
