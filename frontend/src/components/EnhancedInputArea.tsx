import {ReactNode, useState} from "react";

type InputProps = React.DetailedHTMLProps<React.InputHTMLAttributes<HTMLInputElement>, HTMLInputElement> & {
    isTextArea?: false
}

type TextAreaProps = React.DetailedHTMLProps<React.TextareaHTMLAttributes<HTMLTextAreaElement>, HTMLTextAreaElement> & {
    isTextArea: true
}

type Props = (InputProps | TextAreaProps)
    &
    {
        validationMessage: string
        labelMessage: string
    }

export const EnhancedInputArea = (props: Props) => {
    const [isInvalid, setIsInvalid] = useState(false);
    const {id, labelMessage, validationMessage} = props

    let inputElement: ReactNode;
    if (props.isTextArea) {
        const {validationMessage: _validationMessage, labelMessage: _labelMessage, className, ...rest} = props;
        inputElement = (

            <textarea
                className={`w-full border-black valid:border-black rounded-md peer ${isInvalid ? "border-red-700" : ""}  ${className}`}
                onInvalid={() => setIsInvalid(true)}
                {...rest}/>
        )
    } else {
        const {validationMessage: _validationMessage, labelMessage: _labelMessage, className, ...rest} = props;
        inputElement = (
            <input
                className={`w-full border-black valid:border-black rounded-md peer ${isInvalid ? "border-red-700" : ""}  ${className}`}
                onInvalid={() => setIsInvalid(true)}
                {...rest}/>
        )

    }

    return (
        <div className="relative m-2">

            <label className="block text-violet-900 text-lg" htmlFor={id}>{labelMessage}</label>
            {isInvalid && <span
                className="peer-valid:hidden block text-red-700 text-lg absolute right-0 top-0">{validationMessage}</span>}
            {inputElement}

        </div>
    );
};

export default EnhancedInputArea;