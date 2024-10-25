import pandas as pd

# Replace the file path with the correct path to your CSV file
csv_file_path = '/Users/janusquinlan/projects/neu/cs5200/recipes_dataset/recipes_full_dataset.csv'

# Read the entire CSV file
df_full = pd.read_csv(csv_file_path)

# Read the CSV file in chunks
chunk_size = 100000
df_reader = pd.read_csv(csv_file_path, chunksize=chunk_size)

# Iterate over the chunks and save them to separate CSV files
for i, chunk in enumerate(df_reader):
    # Use a standard string format method for compatibility
    file_path = '/Users/janusquinlan/projects/neu/cs5200/recipes_dataset/recipes_dataset_part_{}.csv'.format(i)
    chunk.to_csv(file_path, index=False)
