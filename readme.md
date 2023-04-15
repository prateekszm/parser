
# PDF parser

PDF parser can extract data from pdf by converting pdf to text using Apache PDF Box. By using regex we can select necessary value from pdf. Highly customizible can support different pdf format. All the regex should be configured in database.
## Appendix
The `main` method contains the folder path that that contains pdf. It can recrsively scan all your folder that are present in inside your folder path and fetch all pdf path as an `ArrayList`. It will make an entry in `file_attachment` after scaning your given path. You have to make entry in differnt tables to configure. `file_format` table is used to uniquely identify which format is this. According to format we will built the meta data that are required to parse the pdf.


## Authors

- [prateek](https://www.github.com/prateekszm)


## Roadmap

- Add multipage pdf support

- Add spring support

- Add convert from xml to json

- Add support for xls and xlsv file
- Add better `pdfToTextConveter` class


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd parser
```

Install dependencies

```bash
  maven update 
```

Start the parser

```bash
 run main class
```

